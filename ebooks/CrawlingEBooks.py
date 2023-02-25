import re
import requests

# url = "https://www.kanunu8.com/book3/8365/"
url = "https://www.kanunu8.com/book3/8276/"
txt = requests.get(url).content.decode("gbk")

m1 = re.compile(r'<strong><font color="#dc143c">(.+)</font></strong>')
# print(m1.findall(txt)[0].center(23, '='))

m2 = re.compile(r'<td( width="25%")?><a href="(.+\.html)">(.+)</a>')
raw = m2.findall(txt)

SHJ = [(i[2], url + i[1]) for i in raw]  # i[2]为每一章节标题，url+i[1]为每章节的内容链接
# print("小说目录已加载完毕!")

m3 = re.compile(r'<p>(.+)</p>', re.S)  # 每章节小说内容
m4 = re.compile(r"<br />")  # <br />小说内容中的符号

for i in SHJ:
    with open("藏海花/"+i[0]+".txt", "a") as f:
        # print(i[0])
        t1 = requests.get(i[1]).content.decode("gbk").replace("&nbsp;", " ").replace("&quot;", "").strip()  # 每章节小说内容获取
        nr = m3.findall(t1)  # [0]
        nrl = m4.sub('', nr[0])
        print(i[0]+".txt")
        f.write(nrl)
        f.write("\n")

