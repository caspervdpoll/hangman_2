f = open('24.txt', 'w');
words = open('words.txt', 'r')
query = "INSERT INTO WORDS_24 (id, value) VALUES "
counter = 0
print_counter = 0
for word in words.read().split("\n"):
	#print word
	if len(word) == 24:
		counter += 1
		print_counter += 1
		if counter == 300:
			counter = 1
			query += ";\n"
			query += "INSERT INTO WORDS_24 (id, value) VALUES "
		query += "(" + str(print_counter) + ", '" + word + "'), "

query += ";"
f.write(query);
f.close()
words.close()