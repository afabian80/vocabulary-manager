package com.github.afabian80.vocabulary_manager

class SubtitleProcessor {
	def text
	def tokens = []

	def tokenize() {
		if(!text) {
			throw new Exception('Text property not set')
		}
		return tokens.size
	}
}