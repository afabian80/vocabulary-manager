package com.github.afabian80.vocabulary_manager

import groovy.util.GroovyTestCase

class SubtitleProcessorTest extends GroovyTestCase {
	
	void testTokenize() {
		SubtitleProcessor sp = new SubtitleProcessor()
		sp.text = '''
			2
			00:00:55,889 --> 00:01:01,260
			IAN: Every living person on this planet
			has their own unique pair of eyes.
		'''
		sp.tokenize()
		assert sp.tokens.size == 13
	}
}