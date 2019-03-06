import hashlib
import hmac
import Crypto


e = "8e15ad6e5f26fe0585707dc97ce967c6ffaac0cacd2037c4c6c63a503ceea1e6U2FsdGVkX1+yNXzSLU+2HUI7GTRvpl9BdTD1OPX5iMZgHlYay52uv5t/UEHRkeKLJyFjgCoV2nxfL17HPAz8e3bQy0tm0VtorCHXuW/IauNEynVQygPQzaob/1eRihkfYf1XW59GQscAf9uDz93Dub7qiIn5WMIH6hzkM1yDbDBXb6U4GdZQWYsqoFStfyBGaQ04DnnrN3qU/kSs3ciVGmWyW3vuPSHw26VwmINr1jF16DKGK8YWBExVgu9zhiNBaxyQIuWs2SX2BVHokVBOKg=="
lines = open('rockyou.txt', 'r').read().split('\n')



for i in range(len(lines)):
    key = e[0:64]
    c = e[64:]
    message = hashlib.sha256(lines[i].strip()).hexdigest()
    signature = hmac.new(c, message, hashlib.sha256).hexdigest()
    if (str(signature) == key):
        print("Got it..!!! : ", lines[i])
        break
    else:
        print("."),