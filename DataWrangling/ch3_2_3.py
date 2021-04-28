#引入正则表达式模块
import re

states = ['  Alabama ','Georgial','Georgia', 'georgia', 'FlOrIda', 'south  carolina##', 'West virginia?']
#格式化数据
#strip():方法用于移除字符串头尾指定的字符（默认为空格或换行符）或字符序列。
#title():方法返回"标题化"的字符串,就是说所有单词都是以大写开始，其余字母均为小
#re.sub():字符串替换
#正则表达式[!#?]:一个字符集合[]，
def clean_String(strings):
    result = [ (re.sub('[!#?]','',value.strip()).title())for value in strings ]
    return result

print(clean_String(states))

#函数对象
def remove_punctuation(value):
    return re.sub('[!#?]', '',value)

#函数对象
clean_ops = [str.strip, remove_punctuation, str.title]

def clean_String_ops(strings, ops):
    result = []
    for value in strings:
        for function in ops:
            value = function(value)
        result.append(value)
    return result
print(clean_String_ops(states,clean_ops))