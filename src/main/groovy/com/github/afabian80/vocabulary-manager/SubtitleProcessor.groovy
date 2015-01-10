package com.github.afabian80.vocabulary_manager

class SubtitleProcessor {
	def text
	def tokens = []

	def tokenize() {
		if(!text) {
			throw new Exception('Text property not set')
		}
		text.eachLine { line ->
			def parts = line.tr(',.:;-\'">?!$', ' ').split(' ').findAll { it.length() > 0 }.collect { it.toLowerCase() }
			tokens.addAll(parts)
		}
		tokens.sort()
		tokens.unique(true)
		return tokens.size
	}

	def dropNonAspell40Words() {
		def dictFile = this.getClass().getResource( '/aspell_40_lower.txt' )
		def bigDict = new File(dictFile.path) as String[]
		tokens = tokens.findAll { bigDict.contains(it) }
	}

	def listUnkownWords() {
		def myVocabFile = this.getClass().getResource( '/my_vocabulary.txt' )
		def myVocab = new File(myVocabFile.path) as String[]
		def unknownWords = tokens.findAll { word ->
			boolean found = myVocab.contains(word)	// look up word
			def possibleRoots = possibleRootWordsFor(word)	// try to find root word if it is formed with -(e)s, -(e)d, -ly, -(e)r, -ing, -able
			possibleRoots.each { root ->
				if(myVocab.contains(root)) {
					println "Ignoring $word because of $root"
					found = true
				}
			}
			return !found 
		}
		return unknownWords
	}

	def possibleRootWordsFor(word) {
		def possibleRoots = []
		def endings = 'ed d ing er est ly less ness ation s es ment'.split(' ')
		endings.each { end ->
			if(word.length() > end.length() + 2) {
				if(word.endsWith(end)) {
					def root = word.substring(0, word.length() - (end.length()))
					//println "Adding $root instead of $word"
					possibleRoots.add(root)
				}
			}
		}
		return possibleRoots
	}
}