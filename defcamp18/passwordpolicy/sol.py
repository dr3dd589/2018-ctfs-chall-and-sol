import requests
import string
f = ""
for c in string.printable:
    f += c
if len(f) < 1337:
    f = f+"A"*2000

print(f)
# r = requests.post('https://password-policy.dctfq18.def.camp/', data = {'user':'admin@leftover.dctf','pass':f})
# print(r.content)