import requests,base64,codecs
import urllib
import httplib2
from base64 import b64encode, b64decode

http = httplib2.Http()

url = "http://2018shell1.picoctf.com:43731/login"
body = {'user': 'drAAAAAAAAAAAAAAAA', 'password': '123'}
headers = {'Content-type': 'application/x-www-form-urlencoded'}
response, content = http.request(url, 'POST', headers=headers, body=urllib.urlencode(body))
print(response['set-cookie'])



k = list(b64decode(response['set-cookie'][7:-8],'base64'))
k[10] = chr(ord(k[10])^ord('1'))
s = b64encode(''.join(k),'base64')
response['set-cookie'] = "cookie={0}".format(s).replace('\n','')
headers = {'Content-type': 'application/x-www-form-urlencoded','Cookie': response['set-cookie']}


url = "http://2018shell1.picoctf.com:43731/flag"
res, content = http.request(url, 'GET', headers=headers)
print(content)
print('No')