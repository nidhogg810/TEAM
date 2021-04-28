#匿名函数 lambda
#sort 函数中用作排序规则
strings = ['foo', 'card', 'bar', 'aaaa', 'abab']
#lambda x ->定义函数变量
strings.sort(key=lambda x:len(set(list(x))))
print(strings)
def _key(x):
    return len(set(list(x)))

strings2 = ['foo', 'card', 'bar', 'aaaa', 'abab']
strings2.sort(key=_key)
print(strings2)
print(_key(strings2))
