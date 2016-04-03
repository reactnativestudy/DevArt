unit sec_md5;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, jni2, jni_utils, lbcipher, lbutils, filestream_utils;

function _md5EncryptString(str: PChar): PChar; stdcall;
function _md5EncryptFile(filePath: PChar): PChar; stdcall;
function md5EncryptString(str: PChar): PChar; stdcall;
function md5EncryptFile(filePath: PChar): PChar; stdcall;
function Java_com_hujiang_devart_security_AlgorithmUtils_md5EncryptString(env: PJNIEnv; obj: jobject; str: jstring): jstring; stdcall;
function Java_com_hujiang_devart_security_AlgorithmUtils_md5EncryptFile(env: PJNIEnv; obj: jobject; filePath: jstring): jstring; stdcall;

implementation

var
  Buffer: array[0..1023] of Byte;

function _md5EncryptString(str: PChar): PChar; stdcall;
var
  d: TMD5Digest;
  ret: string;
begin
  StringHashMD5(d, string(str));
  ret := BufferToHex(d, SizeOf(d));
  Result := StrAlloc(Length(ret));
  strcopy(Result, PChar(ret));
end;

function _md5EncryptFile(filePath: PChar): PChar; stdcall;
var
  s: TStream;
  c: TMD5Context;
  d: TMD5Digest;
  ret: string;
  bs: Int64;
begin
  ret := '';
  s := openFileStream(string(filePath));
  if Assigned(s) then begin
    InitMD5(c);
    bs := s.Read(Buffer, SizeOf(Buffer));
    while (bs > 0) do begin
      UpdateMD5(c, Buffer, bs);
      bs := s.Read(Buffer, SizeOf(Buffer));
    end;
    FinalizeMD5(c, d);
    closeFileStream(s);
    ret := BufferToHex(d, SizeOf(d));
  end;
  Result := StrAlloc(Length(ret));
  strcopy(Result, PChar(ret));
end;

function md5EncryptString(str: PChar): PChar; stdcall;
begin
  Result := _md5EncryptString(str);
end;

function md5EncryptFile(filePath: PChar): PChar; stdcall;
begin
  Result := _md5EncryptFile(filePath);
end;

// com.hujiang.devart.security.AlgorithmUtils

function Java_com_hujiang_devart_security_AlgorithmUtils_md5EncryptString(env: PJNIEnv; obj: jobject; str: jstring): jstring; stdcall;
var
  ret: PChar;
begin
  ret  := _md5EncryptString(PChar(jstringToString(env, str)));
  Result := stringToJString(env, string(ret));
end;

function Java_com_hujiang_devart_security_AlgorithmUtils_md5EncryptFile(env: PJNIEnv; obj: jobject; filePath: jstring): jstring; stdcall;
var
  ret: PChar;
begin
  ret  := _md5EncryptFile(PChar(jstringToString(env, filePath)));
  Result := stringToJString(env, string(ret));
end;

end.
