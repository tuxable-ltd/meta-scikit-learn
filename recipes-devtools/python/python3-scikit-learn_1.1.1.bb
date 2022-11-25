SUMMARY = "A set of python modules for machine learning and data mining"
HOMEPAGE = "http://scikit-learn.org"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d187f5bc5e3881c1255dc1489493aac8"

SRC_URI = "git://github.com/scikit-learn/scikit-learn.git;branch=1.1.X;protocol=https \
           file://scikit-1.1.1-numpy-include-workaround.patch \
           "

SRCREV = "80598905e517759b4696c74ecc35c6e2eb508cff"
S = "${WORKDIR}/git"

inherit setuptools3 pkgconfig python3-dir

export PYTHON_CROSSENV = "1"
export SKLEARN_BUILD_PARALLEL = "${@oe.utils.cpu_count()}"
export NPY_PKG_CONFIG_PATH = "${WORKDIR}/npy-pkg-config"
export NUMPY_INCLUDE_PATH = "${STAGING_DIR_TARGET}${libdir}/python${PYTHON_BASEVERSION}/site-packages/numpy/core/include"

# Tell Numpy to look in target sysroot site-packages directory for libraries
LDFLAGS:append = " -L${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/numpy/core/lib"

do_compile:prepend() {
	echo "[ALL]" > ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	mkdir -p ${WORKDIR}/npy-pkg-config
	cp ${STAGING_DIR_TARGET}${libdir}/python${PYTHON_BASEVERSION}/site-packages/numpy/core/lib/npy-pkg-config/* ${WORKDIR}/npy-pkg-config
	sed -i 's&prefix=${pkgdir}&prefix=${STAGING_DIR_TARGET}${libdir}/python${PYTHON_BASEVERSION}/site-packages/numpy/core&g' ${WORKDIR}/npy-pkg-config/npymath.ini
	sed -i 's&prefix=${pkgdir}&prefix=${STAGING_DIR_TARGET}${libdir}/python${PYTHON_BASEVERSION}/site-packages/numpy/core&g' ${WORKDIR}/npy-pkg-config/mlib.ini
}

DEPENDS += " \
	python3-cython-native \
	python3-numpy-native \
	python3-pythran-native \
	python3-scipy-native \
	python3-numpy \
	python3-scipy \
	python3-cython \
"

RDEPENDS:${PN} += "python3-numpy python3-scipy python3-joblib python3-threadpoolctl python3-pytest"
