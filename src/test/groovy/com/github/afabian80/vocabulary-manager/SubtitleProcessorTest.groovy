package com.github.afabian80.vocabulary_manager

import groovy.util.GroovyTestCase

class SubtitleProcessorTest extends GroovyTestCase {

	SubtitleProcessor sp

	void setUp() {
		sp = new SubtitleProcessor()
	}
	
	void testEmptyText() {
		shouldFail {
			sp.tokenize()
		}
	}

	void testTokenize() {
		sp.text = '''
			2
			00:00:55,889 --> 00:01:01,260
			IAN: Every living person on this planet
			has their own unique pair of eyes.
		'''.stripIndent()
		sp.tokenize()
		assert sp.tokens.size == 14
	}

	void testUnique() {
		sp.text = '''
			2
			00:00:55,889 --> 00:01:01,260
			IAN: Every living person on this planet
			has their own unique pair of eyes.
			IAN: Every living person on this planet
			has their own unique pair of eyes.
		'''.stripIndent()
		sp.tokenize()
		assert sp.tokens.size == 14
	}

	void testLoadFile () {
		def subtitleFile = this.getClass().getResource( '/com/github/afabian80/vocabulary-manager/sample1.srt' )
		sp.text = subtitleFile.text
		sp.tokenize()
		assert sp.tokens.size == 1492
	}

	void testDropPossiblyMisspelled() {
		sp.text = '''
			2
			00:00:55,889 --> 00:01:01,260
			IAN: Every living person on this planet
			has their own unique pair of eyes.
		'''.stripIndent()
		sp.tokenize()
		sp.dropNonAspell40Words()
		assert sp.tokens.size == 13	// IAN should be dropped
	}

	void testLoadFileWithDropping () {
		def subtitleFile = this.getClass().getResource( '/com/github/afabian80/vocabulary-manager/sample1.srt' )
		sp.text = subtitleFile.text
		sp.tokenize()
		sp.dropNonAspell40Words()
		assert sp.tokens.size == 1368
	}

	void testListUnkownWords() {
		def subtitleFile = this.getClass().getResource( '/com/github/afabian80/vocabulary-manager/sample1.srt' )
		def vocabFile = this.getClass().getResource( '/my_vocabulary.txt' )
		sp.text = subtitleFile.text
		sp.vocabFile = vocabFile.path
		sp.tokenize()
		sp.dropNonAspell40Words()
		def unknownWords = sp.listUnkownWords()
		assert unknownWords.size == 367
	}
}