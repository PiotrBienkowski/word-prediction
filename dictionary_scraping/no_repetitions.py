import time

tab = open("all_dictionary_1675535541.txt").read().split("\n")
newFile = open("no_repetitions_all_dictionary_" + str(int(time.time())) + ".txt", "w")

i = 0
while i < len(tab):
    newFile.write(tab[i] + "\n")
    tmp = i
    while i < len(tab) and tab[tmp].split()[:-1] == tab[i].split()[:-1]:
        i += 1

newFile.close()