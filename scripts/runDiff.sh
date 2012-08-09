#!/bin/bash -ex
diff <(./cbGame1.sh) Game1.out
diff <(./cbGame2.sh) Game2.out


