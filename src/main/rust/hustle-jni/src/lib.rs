extern crate hustle_api;
extern crate jni;

use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::{jlong, jboolean, jint};
use hustle_api::{HustleConnection, HustleResult};

#[no_mangle]
pub extern "system" fn Java_io_hustle_HustleJNI_hustleConnectionNew(
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
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleConnectionExecuteQuery(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jlong {
    let result = execute(env, connection_ptr, sql).unwrap().unwrap();
    Box::into_raw(Box::new(result)) as jlong
}

#[no_mangle]
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleConnectionExecuteUpdate(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jint {
    let _ = execute(env, connection_ptr, sql).unwrap();
    0 as jint
}

#[no_mangle]
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleConnectionExecute(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jboolean {
    execute(env, connection_ptr, sql)
        .unwrap()
        .is_some() as jboolean
}

#[no_mangle]
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleConnectionClose(
    _env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
) {
    Box::from_raw(connection_ptr as *mut HustleConnection);
}

#[no_mangle]
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleResultHasRow(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong,
    row: jint,
) -> jboolean {
    let result = &*(result_ptr as *mut HustleResult);
    result.get_row(row as usize).is_some() as jboolean
}

#[no_mangle]
pub unsafe extern "system" fn Java_io_hustle_HustleJNI_hustleResultGetLong(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong,
    row: jint,
    col: jint,
) -> jlong {
    let result = &*(result_ptr as *mut HustleResult);
    result.get_row(row as usize)
        .unwrap_or_else(|| panic!("Row {} is out of bounds", row as usize))
        .get_i64(col as usize - 1)
        .unwrap_or_else(|| panic!("Column {} is out of bounds", col as usize))
        as jlong
}

unsafe fn execute(
    env: JNIEnv,
    connection_ptr: jlong,
    sql: JString
) -> Result<Option<HustleResult>, String> {
    let connection = &mut *(connection_ptr as *mut HustleConnection);
    let sql_string: String = env.get_string(sql)
        .expect("Could not get SQL from Java")
        .into();
    connection.execute(sql_string)
}
