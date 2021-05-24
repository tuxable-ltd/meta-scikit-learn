DEPENDS += "openblas"
RDEPENDS_${PN} += "${PYTHON_PN}-multiprocessing"

do_compile_prepend() {
	echo "[ALL]" > ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	echo "[openblas]" >> ${S}/site.cfg
	echo "libraries = openblas" >> ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	echo "[lapack]" >> ${S}/site.cfg
	echo "libraries = lapack" >> ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

}
