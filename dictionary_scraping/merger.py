import os
import time

path = 'dictionaries'
tmp = []
for fileName in os.listdir(path):
    tmp.append(fileName)
tmp.sort()

newFile = open("all_dictionary_" + str(int(time.time())) + ".txt", "w")

for i in tmp:
    tab = open(path + "/" + i).read().split("\n")
    print(tab[0])
    for j in tab:
        if len(j) > 1:
            newFile.write(j + "\n")

newFile.close()