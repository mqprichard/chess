#!/bin/bash -ex
diff <(./runGame1.sh) Game1.out
diff <(./runGame2.sh) Game2.out


