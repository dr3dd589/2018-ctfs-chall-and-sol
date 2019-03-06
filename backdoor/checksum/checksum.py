goal = 43160
def crc16(data):
    crc = 65535
    for i in range(len(data)):
        x = ((crc >> 8) ^ ord(data[i])) & 255
        x ^= x >> 4
        crc = ((crc << 8) ^ (x << 12) ^ (x << 5) ^ x) & 65535
    return crc


from itertools import combinations
for item in combinations('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', 3):
    if crc16(''.join(item)) == goal:
        print("Got it...!!!! : ", ''.join(item))
        break
    else:
        print("Nothing")


