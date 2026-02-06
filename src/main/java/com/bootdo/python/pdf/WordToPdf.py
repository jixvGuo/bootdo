import subprocess
import os
import platform
import sys

sysType = platform.platform()

try:
    from win32com.client import constants, gencache
except ImportError:
    constants = None
    gencache = None


def doc2pdf(docPath, pdfPath):
    """
        convert a doc/docx document to pdf format
        :param doc: path to document
        """
    docPathTrue = os.path.abspath(docPath)  # bugfix - searching files in windows/system32
    if 'Linux' in sysType:
        return doc2pdf_linux(docPathTrue, pdfPath)
    # name, ext = os.path.splitext(doc)
    # try:
    #     word = client.DispatchEx("Word.Application")
    #     worddoc = word.Documents.Open(doc)
    #     worddoc.SaveAs(name + '.pdf', FileFormat=17)
    # except Exception:
    #     raise
    # finally:
    #     worddoc.Close()
    #     word.Quit()
    word = gencache.EnsureDispatch('Word.Application')
    doc = word.Documents.Open(docPathTrue, ReadOnly=1)
    doc.ExportAsFixedFormat(pdfPath,
                            constants.wdExportFormatPDF,
                            Item=constants.wdExportDocumentWithMarkup,
                            CreateBookmarks=constants.wdExportCreateHeadingBookmarks)
    word.Quit(constants.wdDoNotSaveChanges)


def doc2pdf_linux(docPath, pdfPath):
    print("----->>>")
    print(docPath)
    print(pdfPath)
    """
    convert a doc/docx document to pdf format (linux only, requires libreoffice)
    :param doc: path to document
    """
    cmd = 'libreoffice7.0 --headless --convert-to pdf'.split() + [docPath] + ['--outdir'] + [pdfPath]
    print(cmd)
    p = subprocess.Popen(cmd, stderr=subprocess.PIPE, stdout=subprocess.PIPE)
    p.wait()
    stdout, stderr = p.communicate()
    if stderr:
        raise subprocess.SubprocessError(stderr)


if __name__ == '__main__':
    # wordPath = '/root/workspace/3430981940_.docx'
    # pdfPath = '/root/workspace/3430981940_.pdf'
    doc2pdf(sys.argv[1], sys.argv[2])
    # doc2pdf(wordPath, pdfPath)
