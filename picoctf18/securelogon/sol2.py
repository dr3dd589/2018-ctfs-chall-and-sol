from base64 import b64encode, b64decode
from sys import argv


def xor(a, b):
    """
    return a bytearray constructed by XORing a and b
    """
    out = bytearray()
    for i,c in enumerate(a):
        out.append(c ^ b[i])
    return bytes(out)


def construct_cookie(cookie_value):
    # current and desired plaintext, obtained by observing the cookie
    desired_plaintext = bytearray('{"admin": 0, "pa', 'utf8')
    current_plaintext = bytearray('{"admin": 1, "pa', 'utf8')
    # base64-decode the cookie value
    decoded_cookie = b64decode(cookie_value)
    # split the IV and the message
    original_iv = decoded_cookie[:16]
    # construct the mask for the first block
    desired_iv = xor(xor(desired_plaintext, current_plaintext), bytearray(original_iv))
    # prepend the new IV to the original ciphertext
    altered_ciphertext = desired_iv+decoded_cookie[16:]
    # base64-encode the cookie value
    cookie = b64encode(altered_ciphertext).decode("utf8")
    return cookie


if __name__ == '__main__':
    try:
        cookie = argv[1]
    except IndexError:
        print(f'usage: {argv[0]} [COOKIE]')
    print(construct_cookie(cookie))