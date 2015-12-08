[1] How to compile?
First of all, it is a J2SE project. Unzip this archive on any Win32 or Linux 
machine that support Java-1.6 or above version. It is suggested that you 
choose Eclipse to import and open this project. NetBeans or other Java IDE 
should also be acceptable, but you may have to spend some time on project 
configuration. If any high-lighted errors are shown by the IDE, that might 
be because of mis-configuration, please let us know and we are pleased 
to offer help. After being successfully imported, just try the usual way of 
chosen IDE to build the project.

[2] How to execute?
When you see the main window named "SWN Simulation", please try to click the 
button "Step" several times. When the computing is finished, you may get a hint
dialog saying "please see data analysis", and it is time to click another 
button "Analyze" and you will see the results shown in a figure by another 
window. You may want to change the syntax N, K, P and try to compute again. 
Please note that if the chosen N is very large, say N=5000, the computing will 
cost you quite a waiting time (each step needs more than one minute on a laptop 
PC), and the order of N->K->P and their dependency relationship should be 
understood also, otherwise some runtime error might happen. About the background 
knowledge of this project, please refer to our project report document.

[3] About the programmers.
Shaomin (Samuel) Zhang is responsible for the non-UI part of algorithm coding while Suresh 
Vadlakonda contributes the Java-Swing GUI part of code.
If anything goes unexpected, please contact us via: smicn@foxmail.com