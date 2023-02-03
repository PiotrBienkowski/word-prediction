from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

browser = webdriver.Chrome()
browser.get('https://sjp.pl/sl/lp.phtml?p=1')

# waiting to load the page
wait = WebDriverWait(browser, 10)

element = browser.find_element(by=By.XPATH, value="/html/body/table/tbody/tr[2]/td[1]/a")

print(element.text)

input("Press return to end")
browser.quit()
