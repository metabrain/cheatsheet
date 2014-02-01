int binary_search(int A[], int key, int imin, int imax)
{
  if (imax < imin)
    return KEY_NOT_FOUND;
  else
    {
      int imid = midpoint(imin, imax);
 
      if (A[imid] > key)  // key is in lower subset
        return binary_search(A, key, imin, imid-1);
      else if (A[imid] < key)  // key is in upper subset
        return binary_search(A, key, imid+1, imax);
      else  // key has been found
        return imid;
    }
}