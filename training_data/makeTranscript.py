import time
import speech_recognition as sr
from pydub import AudioSegment

orginalFileName = "file_name"
audio = AudioSegment.from_wav(orginalFileName + ".wav")

file_duration = 2 * 60 * 1000
newFile = open(orginalFileName + "_" + str(int(time.time())) + ".txt", "w")

for i in range(9):
    start_time = i * file_duration
    end_time = (i + 1) * file_duration
    filename = "tmp.wav"
    audio_part = audio[start_time:end_time]
    audio_part.export(filename, format="wav")

    r = sr.Recognizer()
    with sr.AudioFile(filename) as source:
        audio_data = r.record(source)
        text = r.recognize_google(audio_data, language='pl-PL')
        print("----------------------------------------")
        newFile.write(text)
        newFile.flush()
        print(text, i)

newFile.close()