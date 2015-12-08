# SmallWorldNetwork
a Java project with Graph, simulator, computational model and the theory of 6-degrees and so-called Small-world Network <br>
Shaomin (Samuel) Zhang, Suresh Vadlakonda
May, 2015

## Brief
If you are interested with the topic of theories itself, you might like to read them through: <br>
https://en.wikipedia.org/wiki/Small-world_network <br>
https://en.wikipedia.org/wiki/Six_degrees_of_separation <br><br>

This project is to build a simulation software and provide a proof by computational evidence. Well, it is related to Graph 
and you may find the implementations of Floyd and Dijkstra's algorithms in this project, but since for calculating the all-pair 
shortest pathes a complexity of O(N3) is doomed, you may not like the performace of this program. We have seperately designed 
modules: non-GUI domains and GUI parts; we designed the interfaces first, and then designed and implemented them seperately, and 
integrated together finally and saw them work well. <br><br>

This is what input data we use to simulate a network: <br>
![](https://raw.githubusercontent.com/smicn/SmallWorldNetwork/master/doc/N5000K20P001_1a.png)

<br>And we got very good results: <br>
![](https://raw.githubusercontent.com/smicn/SmallWorldNetwork/master/doc/N5000K20P001_1b.png)

See, 6-degrees theory is proved again. What a happy world! You can still find anyone on this earth by making 6 phone calls. 
Seriously, this is indeed the theoretical fundamental of many P2P network protocols and the social networks. <br><br>

## Compilation and Execution
[1] How to compile? <br>
First of all, it is a J2SE project. Unzip this archive on any Win32 or Linux 
machine that support Java-1.6 or above version. It is suggested that you 
choose Eclipse to import and open this project. NetBeans or other Java IDE 
should also be acceptable, but you may have to spend some time on project 
configuration. If any high-lighted errors are shown by the IDE, that might 
be because of mis-configuration, please let us know and we are pleased 
to offer help. After being successfully imported, just try the usual way of 
chosen IDE to build the project. <br><br>

[2] How to execute? <br>
When you see the main window named "SWN Simulation", please try to click the 
button "Step" several times. When the computing is finished, you may get a hint
dialog saying "please see data analysis", and it is time to click another 
button "Analyze" and you will see the results shown in a figure by another 
window. You may want to change the syntax N, K, P and try to compute again. 
Please note that if the chosen N is very large, say N=5000, the computing will 
cost you quite a waiting time (each step needs more than one minute on a laptop 
PC), and the order of N->K->P and their dependency relationship should be 
understood also, otherwise some runtime error might happen. About the background 
knowledge of this project, please refer to our project report document. <br><br>

## Contact me
E-mail: smicn@foxmail.com <br>
Linkedin: https://www.linkedin.com/in/shaomin-zhang-0ba60667
