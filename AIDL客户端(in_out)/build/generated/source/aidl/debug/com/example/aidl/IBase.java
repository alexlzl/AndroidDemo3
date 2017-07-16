/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Android/DEMO/AIDL客户端(in_out)/src/main/aidl/com/example/aidl/IBase.aidl
 */
package com.example.aidl;
public interface IBase extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.aidl.IBase
{
private static final java.lang.String DESCRIPTOR = "com.example.aidl.IBase";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.aidl.IBase interface,
 * generating a proxy if needed.
 */
public static com.example.aidl.IBase asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.aidl.IBase))) {
return ((com.example.aidl.IBase)iin);
}
return new com.example.aidl.IBase.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_add:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _result = this.add(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getUserInfo:
{
data.enforceInterface(DESCRIPTOR);
com.example.aidl.UserInfo _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.aidl.UserInfo.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _result = this.getUserInfo(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getaList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
int _arg0_length = data.readInt();
if ((_arg0_length<0)) {
_arg0 = null;
}
else {
_arg0 = new java.lang.String[_arg0_length];
}
this.getaList(_arg0);
reply.writeNoException();
reply.writeStringArray(_arg0);
return true;
}
case TRANSACTION_setaList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
_arg0 = data.createStringArray();
this.setaList(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_gettList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
_arg0 = data.createStringArray();
this.gettList(_arg0);
reply.writeNoException();
reply.writeStringArray(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.aidl.IBase
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int add(int i, int j) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(i);
_data.writeInt(j);
mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getUserInfo(com.example.aidl.UserInfo userinfo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((userinfo!=null)) {
_data.writeInt(1);
userinfo.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getUserInfo, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void getaList(java.lang.String[] list) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((list==null)) {
_data.writeInt(-1);
}
else {
_data.writeInt(list.length);
}
mRemote.transact(Stub.TRANSACTION_getaList, _data, _reply, 0);
_reply.readException();
_reply.readStringArray(list);
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setaList(java.lang.String[] list) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(list);
mRemote.transact(Stub.TRANSACTION_setaList, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void gettList(java.lang.String[] list) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(list);
mRemote.transact(Stub.TRANSACTION_gettList, _data, _reply, 0);
_reply.readException();
_reply.readStringArray(list);
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getUserInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getaList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setaList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_gettList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public int add(int i, int j) throws android.os.RemoteException;
public java.lang.String getUserInfo(com.example.aidl.UserInfo userinfo) throws android.os.RemoteException;
public void getaList(java.lang.String[] list) throws android.os.RemoteException;
public void setaList(java.lang.String[] list) throws android.os.RemoteException;
public void gettList(java.lang.String[] list) throws android.os.RemoteException;
}
