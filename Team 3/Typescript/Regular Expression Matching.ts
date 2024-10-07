function match(s: string, p: string): void {
    if (p[0] === '*') {
        console.log(false);
        return;
    }

    // Reducción de asteriscos consecutivos
    let pArr: string[] = p.split('');
    let i: number = 0;
    while (i < pArr.length - 1) {
        if (pArr[i] === '' && pArr[i + 1] === '') {
            pArr.splice(i + 1, 1);
        } else {
            i++;
        }
    }
    p = pArr.join('');

    let sIndex: number = 0;
    let pIndex: number = 0;
    let count: number = 0;
    let aux1: string[][] = [[]];  // matriz auxiliar 
    let aux2: string[][] = [[]];

    while (sIndex < s.length || pIndex < p.length) {
        if (pIndex < p.length && sIndex < s.length && (s[sIndex] === p[pIndex] || p[pIndex] === '.')) {
            let sublistsToRemove: string[][] = [];
            aux1.forEach(sublist => {
                if (p[pIndex] === '.') {
                    if (sublist.length < s.length) {
                        sublist.push(s[sublist.length]);
                    }
                } else {
                    sublist.push(s[sIndex]);
                }

                if (sublist.join('') !== s.slice(0, sublist.length)) {
                    sublistsToRemove.push(sublist);
                }
            });

            sublistsToRemove.forEach(sublist => {
                let index = aux1.indexOf(sublist);
                if (index > -1) {
                    aux1.splice(index, 1);
                }
            });
            pIndex++;
            sIndex++;
            continue;
        }

        if (pIndex < p.length && p[pIndex] === '*') {
            if (sIndex >= s.length) {
                sIndex--;
            }

            if (pIndex > 0 && p[pIndex - 1] === '.') {
                aux2 = deepCopy(aux1);
                aux2.forEach(sublist => {
                    sublist.push(s[sIndex]);
                    while (sublist.join('') === s.slice(0, sublist.length)) {
                        aux1.push([...sublist]);
                        sublist.push(s[sIndex]);
                        if (sublist.length > s.length) {
                            break;
                        }
                    }
                });
            } else {
                aux2 = deepCopy(aux1);
                aux2.forEach(sublist => {
                    while (sublist.join('') === s.slice(0, sublist.length)) {
                        aux1.push([...sublist]);
                        sublist.push(sublist[sublist.length - 1]);
                        if (sublist.length > s.length) {
                            break;
                        }
                    }
                });
            }
            pIndex++;
        } else {
            if (pIndex >= p.length) {
                break;
            }

            aux1.forEach(sublist => {
                sublist.push(p[pIndex]);
            });

            aux1.forEach(sublist => {
                if (sublist.join('') === s && pIndex === p.length - 1) {
                    console.log(true);
                    return;
                } else if (sublist.join('') !== s && pIndex === p.length - 1) {
                    count++;
                }
            });

            if (count === aux1.length) {
                console.log(false);
                return;
            } else {
                count = 0;
            }

            if (aux1.length === 0) {
                break;
            }

            pIndex++;
            sIndex++;
        }
    }

    aux1.forEach(sublist => {
        if (sublist.join('') === s) {
            console.log(true);
            return;
        }
    });

    console.log(false);
}

function deepCopy(original: string[][]): string[][] {
    return original.map(sublist => [...sublist]);
}