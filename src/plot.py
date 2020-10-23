import matplotlib.pyplot as plt
from numpy import *
# from mpl_toolkits import mplot3d

def plot_solver_node(filename, x_label):
    x = []
    y_nodes = []
    # y_time = []

    with open(filename, 'r') as fp:
        for line in fp:
            parts = line.split(",")
            x.append(parts[0])
            y_nodes.append(parts[1])
            # y_time.append(parts[2])
    
    plt.xlabel(x_label)
    plt.ylabel('solver nodes')
    plt.plot(x, y_nodes, label="solver nodes")
    # plt.plot(x, y_time, label="solver time")
    plt.legend(loc="upper left")
    plt.axis((3, 7, 200, 4500))
    plt.show()
            
def plot_solver_time(filename, x_label):
    x = []
    y = []

    with open(filename, 'r') as fp:
        for line in fp:
            parts = line.split(",")
            x.append(parts[0])
            y.append(parts[2])
    
    plt.xlabel(x_label)
    plt.ylabel('solver time')
    plt.legend(loc="upper left")
    plt.plot(x, y, label="solver time")
    plt.axis((4, 7, 0, 0.3))
    plt.show()

if __name__ == "__main__":
    # plot_solver_node("evalOPS.csv", "x_label")
    # plot_solver_time("evalAc.csv", "Number of Actions")
    plot_solver_node("evalAc.csv", "Number of Actions")
