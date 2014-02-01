def ternarySearch(f, left, right, absolutePrecision):
    #left and right are the current bounds; the maximum is between them
    if (right - left) < absolutePrecision:
        return (left + right)/2
 
    leftThird = (2*left + right)/3
    rightThird = (left + 2*right)/3
 
    if f(leftThird) > f(rightThird):
        return ternarySearch(f, leftThird, right, absolutePrecision)
    else:
        return ternarySearch(f, left, rightThird, absolutePrecision)
