import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

def ani(i):
    data = pd.read_csv('data.csv')
    plt.cla()

    plt.plot(data['i'], data['Algae'], color='green', label='Algae')
    plt.plot(data['i'], data['Animals'], color='orange', label='Animals')
    plt.plot(data['i'], data['Fish'], color='pink', label='Fish')
    plt.plot(data['i'], data['Sharks'], color='red', label='Sharks')
    plt.plot(data['i'], data['Water'], color='blue', label='Water')

    plt.legend(loc='upper left')
    plt.tight_layout()

ani = FuncAnimation(plt.gcf(), ani, interval=33)
plt.show()