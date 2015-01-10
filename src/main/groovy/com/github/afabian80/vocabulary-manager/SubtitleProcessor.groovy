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
}