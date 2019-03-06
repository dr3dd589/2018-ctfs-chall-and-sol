import requests

for i in range(1000):
        print(i)
        url = "http://noob.bckdr.in:10011/result.php"
        r = requests.post(url=url,data={'answer':i})
        if "gotta" not in r.text:
                print(r.text)