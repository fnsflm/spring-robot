import requests
from bs4 import BeautifulSoup
import re
import json
import sys
import random

url = "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?host_uid=%s&offset_dynamic_id=0"%(sys.argv[1])
# print(url)
user_agents = [
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1',
    'Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11',
    'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36'
]
headers = {
    'User-Agent':random.choice(user_agents)
}

ht = requests.get(url,headers=headers)
js=ht.text
js=json.loads(js)
try:
    did=js['data']['cards'][int(sys.argv[2])]['desc']['dynamic_id']
    print(did)
except Exception :
    print("null")
try:
    js2=json.loads(js['data']['cards'][int(sys.argv[2])]['card'])
    item=js2['item']
    if 'content' in item.keys():
        content=item['content']
        print(repr(content))
    elif 'description' in item.keys():
        content=item['description']
        print(repr(content))
    else:
        print("\'null\'")
except Exception:
    print("\'null\'")

try:
    uname = js['data']['cards'][int(sys.argv[2])]['desc']['user_profile']['info']['uname']
    print(uname)
except Exception:
    print('null')

# resu = r"最新动态\n内容:\n%s\n链接地址：https://t.bilibili.com/%s"%(js2['item']['content'],did)
# print(repr(resu))

