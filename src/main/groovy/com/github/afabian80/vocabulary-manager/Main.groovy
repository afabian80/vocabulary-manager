package com.github.afabian80.vocabulary_manager

class Main {
	static void main(String[] args) {
		if(args.length != 2) {
			println "Error: input files not defined"
			println "Usage: Main sub_file vocab_file"
			System.exit(1)
		}
		Main main = new Main()
		main.run(args[0], args[1])
	}

	void run(subtitleFile, vocabFile) {
		println "Called with $subtitleFile, $vocabFile"
		SubtitleProcessor sp = new SubtitleProcessor()
		sp.text = new File(subtitleFile).text
		sp.vocabFile = new File(vocabFile)
		sp.tokenize()
		sp.dropNonAspell40Words()
		def unknownWords = sp.listUnkownWords()
		unknownWords.each { println it }
	}
}