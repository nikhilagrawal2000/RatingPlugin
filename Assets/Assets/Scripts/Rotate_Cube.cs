using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rotate_Cube : MonoBehaviour {

    public float speed = 30.0f;

    void Update()
    {
        transform.Rotate(speed * Time.deltaTime, 2 * speed * Time.deltaTime, -speed * Time.deltaTime);    
    }
}
