import copy

def match(s, p):
  
    if p[0] == "*":
        print(False)
        return

    i = 0
    while i < len(p) - 1:
        if p[i] == "" and p[i+1] == "":
            p = p[:i] + p[i+1:]
        else:
            i += 1

    i = 0  
    j = 0  
    count = 0
    aux1 = [[]]  
    aux2 = [[]]  

    while i < len(s) or j < len(p):  
        if j < len(p) and (i < len(s) and (s[i] == p[j] or p[j] == ".")):
            
            sublists_to_remove = []
            for sublist in aux1:
                
                if p[j] == ".":
                    if len(sublist) < len(s):  
                        sublist.append(s[len(sublist)])
                else:
                    sublist.append(s[i])
                
                if ''.join(sublist) != s[:len(sublist)]:
                    sublists_to_remove.append(sublist)
            for sublist in sublists_to_remove:
                aux1.remove(sublist)  
            j += 1
            i += 1  
            continue
        
        if j < len(p) and p[j] == "*":
            if i >= len(s):
                i -= 1
            if j > 0 and p[j - 1] == ".":  
                aux2 = copy.deepcopy(aux1)
                for sublist in aux2:

                    sublist.append(s[i])  
                    while ''.join(sublist) == s[:len(sublist)]:
                        aux1.append(sublist.copy())  
                        sublist.append(s[i])
                        if len(sublist) > len(s):
                            break
            else:
                aux2 = copy.deepcopy(aux1)
                for sublist in aux2:
                    while ''.join(sublist) == s[:len(sublist)]:
                        aux1.append(sublist.copy())
                        sublist.append(sublist[-1])
                        if len(sublist) > len(s):
                            break
            j += 1  
        

        else:
            if j >= len(p):
                break
            for sublist in aux1:
                sublist.append(p[j])
            for sublist in aux1:
                if ''.join(sublist) == s and j == len(p) - 1:
                    print(True)
                    return
                elif ''.join(sublist) != s and j == len(p) - 1:
                    count += 1
            if count == len(aux1):
                print(False)
                return
            else:
                count = 0
            if len(aux1) == 0:
                break
            
            j += 1
            i += 1
        
    for sublist in aux1:
        if ''.join(sublist) == s:
            print(True)
            return
    
    print(False)