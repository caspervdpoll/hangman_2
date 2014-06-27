f = open('ids.txt', 'w');
words = open('series.txt', 'r')
for word in words.read().split(";"):
	word = word + "\n";
	print word
	f.write(word);
f.close()
words.close()