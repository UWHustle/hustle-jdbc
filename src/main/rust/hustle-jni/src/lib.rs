extern crate hustle_api;
extern crate jni;

use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::{jlong, jboolean, jint};
use hustle_api::{HustleConnection, HustleResult};

#[no_mangle]
pub extern "system" fn Java_HustleJNI_hustleConnectionNew(
    env: JNIEnv,
    _class: JClass,
    url: JString
) -> jlong {
    let url_string: String = env.get_string(url)
        .expect("Could not get URL from Java")
        .into();
    Box::into_raw(Box::new(HustleConnection::connect(url_string).unwrap())) as jlong
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleConnectionExecuteQuery(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jlong {
    let connection = &mut *(connection_ptr as *mut HustleConnection);
    let sql_string: String = env.get_string(sql)
        .expect("Could not get SQL from Java")
        .into();
    connection.execute(sql_string)
        .map(|result| Box::into_raw(Box::new(result.unwrap())) as jlong)
        .unwrap_or(0 as jlong)
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleConnectionExecuteUpdate(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jint {
    let connection = &mut *(connection_ptr as *mut HustleConnection);
    let sql_string: String = env.get_string(sql)
        .expect("Could not get SQL from Java")
        .into();
    let _ = connection.execute(sql_string);
    0 as jint
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleResultHasRow(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong,
    row: jint,
) -> jboolean {
    let result = &*(result_ptr as *mut HustleResult);
    result.get_row(row as usize).is_some() as jboolean
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleResultGetLong(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong,
    row: jint,
    col: jint,
) -> jlong {
    let result = &*(result_ptr as *mut HustleResult);
    result.get_row(row as usize).unwrap().get_i64(col as usize).unwrap()
}
