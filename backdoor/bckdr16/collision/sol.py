col = "YMApBqHME/F0cmExiU3Tp9s+eEOQjlMOXU29YgAAwAEAAMABAADAAQ==".decode('base64')
print(col,len(col))


def rotr(x, n):
    return ((((x)>>(n)) | ((x) << (32 - (n)))))

def rotl(x, n):
    return (((x)<<(n))|((x)>>(32-(n))))

def hash(num):
	num^=0x24f50094
	num= rotr(num, num&0xF)
	num+=0x2219ab34
	num= rotl(num, num&0xF)
	num*=0x69a2c4fe
	return num

def main(input):
    v6 = 0
    fl = ""
    for i in range(10):
        v3 = hash(ord(input[i*4]))
        v6 = rotr(v3^v6,7)
        fl += str(hex(v6)[2:-1])
    return fl   

if __name__ == '__main__':
    user_input = "A"*40
    print('User input hash : ',main(user_input))
    print('Collision hash : ',main(col))
