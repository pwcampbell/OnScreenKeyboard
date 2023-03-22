import fs from "fs";
import readline from "readline";
export class app {
  run() {
    const keyboardLayout = this.getKeyboardLayout();
    const readLineInterface = readline.createInterface({
      input: fs.createReadStream('input.txt'),
    });
    readLineInterface.on('line', (line: string) => {
      line = line.toLowerCase();
      console.log('line:', line);
      console.log('output:', this.translateInputToKeyBoard(keyboardLayout, line, 0, 0));
    });
  }

  getKeyboardLayout(): string[][] {
    const keyboardLayout: string[][] = [
      ['A', 'B', 'C', 'D', 'E', 'F'],
      ['G', 'H', 'I', 'J', 'K', 'L'],
      ['M', 'N', 'O', 'P', 'Q', 'R'],
      ['S', 'T', 'U', 'V', 'W', 'X'],
      ['Y', 'Z', '1', '2', '3', '4'],
      ['5', '6', '7', '8', '9', '0']
    ];
    keyboardLayout.forEach((row, x) => {
      row.forEach((character, y) => {
        keyboardLayout[x][y] = character.toLowerCase();
      });
    });

    return keyboardLayout;
  }

  translateInputToKeyBoard(keyboardLayout: string[][], input: string, currentX = 0, currentY = 0): string {
    let operations: string[] = [];
    Object.values(input).forEach((character: string) => {
      if (character !== ' ') {
        const target: { x: number; y: number } = this.getTargetValues(character, keyboardLayout);
        while (target.x !== currentX || target.y !== currentY) {
          if (currentY < target.y) {
            operations.push('D');
            currentY++;
          } else if (currentY > target.y) {
            operations.push('U');
            currentY--;
          } else if (currentX < target.x) {
            operations.push('R');
            currentX++;
          } else if (currentX > target.x) {
            operations.push('L');
            currentX--;
          }
        }
        operations.push('#');
      } else if (character === ' ') {
        operations.push('S');
      }
    });
    const output = operations.join(',');

    return output;
  }
  getTargetValues(character: string, keyboardLayout: string[][]): { x: number, y: number } {
    let x = -1;
    let y = -1;
    keyboardLayout.forEach((row, yIndex) => {
      const xIndex = row.indexOf(character);
      if (xIndex > -1) {
        x = xIndex;
        y = yIndex;
      }
    });
    const result = { x, y };
    if (x < 0 || y < 0) {
      throw new Error('x or y was not found');
    }

    return result;
  }
}