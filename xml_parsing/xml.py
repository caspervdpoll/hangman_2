from xml.sax.handler import ContentHandler
import xml.sax
import sys



class textHandler(ContentHandler):
	f = open('words.txt', 'w');
	#f.flush()
#	print f
	sys.stdout = f
	def characters(self, ch):
		#print "."
		sys.stdout.write(ch.encode("Latin-1"))
		#f.write('popep')
		#f.close()

parser = xml.sax.make_parser()
handler = textHandler()
parser.setContentHandler(handler)
parser.parse("words.xml")
