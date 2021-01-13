SUMMARY = "A set of python modules for machine learning and data mining"
HOMEPAGE = "http://scikit-learn.org"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c8d7e027b3e67a2b1fe7fe85ebeb17d7"

SRC_URI = "git://github.com/scikit-learn/scikit-learn.git;branch=master"
SRCREV = "34de1b9b2122783601b245450a1885d18558ac81"
S = "${WORKDIR}/git"

inherit setuptools3

export PYTHON_CROSSENV = "1"

DEPENDS += "python3-numpy-native python3-scipy-native python3-cython-native"
RDEPENDS_${PN} += "python3-numpy python3-scipy python3-joblib python3-threadpoolctl python3-pytest"

DEFAULT_PREFERENCE = "-1"
