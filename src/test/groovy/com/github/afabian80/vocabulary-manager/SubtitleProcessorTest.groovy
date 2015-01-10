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
}