Mahalanobis for Weka
========================

An implementation of the [Mahalanobis distane metrics](http://en.wikipedia.org/wiki/Mahalanobis_distance)  suitable for integration in [Weka](http://www.cs.waikato.ac.nz/ml/weka/).

Can operate on matrices with feature values or Weka instances.

You will need the [Weka 3.6 jar](http://prdownloads.sourceforge.net/weka/weka-3-6-12.zip).

Sample output


 A = 
[2.0, 2.0]
[2.0, 5.0]
[6.0, 5.0]
[7.0, 3.0]
[4.0, 7.0]
[6.0, 4.0]
[5.0, 3.0]
[4.0, 6.0]
[2.0, 5.0]
[1.0, 3.0]



 B = 
[6.0, 5.0]
[7.0, 4.0]
[8.0, 7.0]
[5.0, 6.0]
[5.0, 4.0]


Mahalanobis distance between matrices A and B is 1.410