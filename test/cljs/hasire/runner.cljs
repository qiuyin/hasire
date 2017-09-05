(ns hasire.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [hasire.core-test]))

(doo-tests 'hasire.core-test)
