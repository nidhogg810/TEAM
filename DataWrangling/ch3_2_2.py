#返回多个值---返回一个元组对象
def _return_tuple():
    a = 5
    b = 6
    c = 7
    return a, b, c
#返回一个字典对象
def _return_map():
    a = 5
    b = 6
    c = 7
    return {'a':a, 'b':b, 'c':c}
#自动解包
a, b, c = _return_tuple()
print(a,b,c)
maping = _return_map()
print(maping)