from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import time
# coding: utf-8

# print("Enter a letter:")
letter = input()

print("scraping:", letter)
newFile = open("dictionaries/" + letter + "_dictionary_" + str(int(time.time())) + ".txt", "w")

browser = webdriver.Chrome()
browser.get('https://sjp.pwn.pl/lista/')
wait = WebDriverWait(browser, 10)

nextPageButton = browser.find_element(by=By.XPATH, value="/html/body/div[3]/div[5]/div/div[1]/div/div[1]/div[1]/div[2]/div[6]/div/div/ul/li[11]/a")
nextPageButton.click()
time.sleep(10)

cnt = 1
lastPage = False
while not lastPage:
    link = "https://sjp.pwn.pl/lista/" + letter + ";" + str(cnt) + ".html"
    browser.get(link)
    tmp = 0
    for i in range(3):
        for j in range(20):
            elemetXPath = "/html/body/div[3]/div[5]/div/div[1]/div/div[1]/div[1]/div[2]/div[5]/div/div/div[" + str(i + 1) + "]/ul/li[" + str(j + 1) + "]/a"
            try:
                element = browser.find_element(by=By.XPATH, value=elemetXPath)
                newFile.write(str(element.text) + "\t" + str(element.get_attribute('href')) + "\n")
                tmp += 1
            except:
                break
    if tmp == 0:
        lastPage = True
    print(cnt, tmp)
    cnt += 1

newFile.close()
# input("Press return to end")
browser.quit()