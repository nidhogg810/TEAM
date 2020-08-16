import numpy as np
import matplotlib.pyplot as plt
import pandas as pd 
import seaborn as sns
import statsmodels as sm

#创建一个data字典 key:i value:range(7)
data = {i : np.random.randn() for i in range(7)}
print(data)
#在ipyhton命令行视图中'data?'可以显示data变量的详细信息