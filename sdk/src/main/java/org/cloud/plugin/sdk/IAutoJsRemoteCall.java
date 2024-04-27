package org.cloud.plugin.sdk;


import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.Map;

public interface IAutoJsRemoteCall extends IInterface {
    Map call(String var1, Map var2, IAutoJsRemoteCallback var3) throws RemoteException;

    public abstract static class Stub extends Binder implements IAutoJsRemoteCall {
        private static final String DESCRIPTOR = "org.cloud.plugin.sdk.IAutoJsRemoteCall";
        static final int TRANSACTION_call = 1;

        public Stub() {
            this.attachInterface(this, "org.cloud.plugin.sdk.IAutoJsRemoteCall");
        }

        public static IAutoJsRemoteCall asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("org.cloud.plugin.sdk.IAutoJsRemoteCall");
                return (IAutoJsRemoteCall)(iin != null && iin instanceof IAutoJsRemoteCall ? (IAutoJsRemoteCall)iin : new Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = "org.cloud.plugin.sdk.IAutoJsRemoteCall";
            switch (code) {
                case 1:
                    data.enforceInterface(descriptor);
                    String _arg0 = data.readString();
                    ClassLoader cl = this.getClass().getClassLoader();
                    Map _arg1 = data.readHashMap(cl);
                    IAutoJsRemoteCallback _arg2 = IAutoJsRemoteCallback.Stub.asInterface(data.readStrongBinder());
                    Map _result = this.call(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    reply.writeMap(_result);
                    return true;
                case 1598968902:
                    reply.writeString(descriptor);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        public static boolean setDefaultImpl(IAutoJsRemoteCall impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            } else {
                return false;
            }
        }

        public static IAutoJsRemoteCall getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        private static class Proxy implements IAutoJsRemoteCall {
            private IBinder mRemote;
            public static IAutoJsRemoteCall sDefaultImpl;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "org.cloud.plugin.sdk.IAutoJsRemoteCall";
            }

            public Map call(String action, Map args, IAutoJsRemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                Map var8;
                try {
                    _data.writeInterfaceToken("org.cloud.plugin.sdk.IAutoJsRemoteCall");
                    _data.writeString(action);
                    _data.writeMap(args);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        ClassLoader cl = this.getClass().getClassLoader();
                        Map _result = _reply.readHashMap(cl);
                        return _result;
                    }

                    var8 = Stub.getDefaultImpl().call(action, args, callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return var8;
            }
        }
    }

    public static class Default implements IAutoJsRemoteCall {
        public Default() {
        }

        public Map call(String action, Map args, IAutoJsRemoteCallback callback) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }
}
