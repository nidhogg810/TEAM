#列表推导式
strings = ['a','as','bat','car','dov','python']
print([x.upper() for x in strings if len(x)>2])
#集合推导式
set_comp = {len(x) for x in strings if x.count('a')>0}
print(set_comp)
#字典推导式
loc_mapping = {val : index for index, val in enumerate(strings) if index != 0}
print(loc_mapping)
#嵌套推导式
all_data = [['John','Emily','Michael','Mary','Steven'],['Maria','Juan','Javier','Natalia','Pilar']]
#嵌套方式1:
#like:
#   result = []
#   for names in all_data:
#       for name in names:
#           if name.count('e') >= 2 or name.count('a')>=2 :
#               result.append(name)
result = [ name for names in all_data for name in names if name.count('e') >= 2 or name.count('a')>=2]
print(result)
#嵌套方式2:会形成包含列表的列表，而非扁平式
result2 = [ [name for name in names if name.count('e') >= 2 or name.count('a')>=2 ] for names in all_data ]
print(result2)