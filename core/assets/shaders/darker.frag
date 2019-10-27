uniform sampler2D u_texture;
varying vec2 v_texCoords;

void main()
{
    vec4 tex = texture2D(u_texture, v_texCoords);
    gl_FragColor = vec4(tex.r * 0.4, tex.g * 0.4, tex.b * 0.4, tex.a) ;
}