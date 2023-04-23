tab = open("input/no_repetitions_all_dictionary_1675539110.txt").read().split("\n");
tab = open("input/all_transcripts_1676637361.txt").read().split()

words = []

for i in tab:
    # tmp = i.split("\t")
    tmp = i
    if len(tmp) == 5:
        words.append(tmp)

letters = []
final = []

print(words)

for i in words:
    error = False
    for j in i:
        if j in [" ", ".", "-"]:
            error = True
        if j in letters:
            error = True
    if not error:
        final.append(i)
        for j in i:
            letters.append(j)

print(final)