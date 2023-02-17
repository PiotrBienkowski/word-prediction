import os
import time

path = 'final_files'
tmp = []
for fileName in os.listdir(path):
    tmp.append(fileName)
tmp.sort()

newFile = open("all_transcripts_" + str(int(time.time())) + ".txt", "w")

for i in tmp:
    tab = open(path + "/" + i).read().split("\n")
    print(tab[0])
    for j in tab:
        if len(j) > 1:
            newFile.write(j + " ")

newFile.close()