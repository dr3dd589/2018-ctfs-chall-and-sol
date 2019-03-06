import requests

url = "http://18.205.93.120:1216"
r = requests.post(url=url,data={'plain':'a'})


after_url = r.url
enc = after_url[26:]
for i in range(256):
    orcl_url = url + "/"+'0'*31 + chr(i) + enc[32:]
    s = requests.get(url=orcl_url)
    res = s.text
    if "Invalid Padding" in res:
        print('Invalid Padding',i)
    elif "Invalid Encoding" in res:
        print('Invalid Encoding',i)
    else:
        print(orcl_url)
        print('True Padding')
        print(s.text)